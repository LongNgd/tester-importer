package energy.at.testerimporter.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import energy.at.testerimporter.common.enums.AssetKind;
import energy.at.testerimporter.domain.model.*;
import energy.at.testerimporter.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class JsonTransformerLoader {

    private final LifecycleDateRepository lifecycleDateRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final FrequencyRepository frequencyRepository;
    private final ProductAssetModelRepository productAssetModelRepository;
    private final AssetRepository assetRepository;
    private final OldPowerTransformerInfoRepository oldPowerTransformerInfoRepository;
    private final VoltageRepository voltageRepository;
    private final BaseVoltageRepository baseVoltageRepository;
    private final VoltageLevelRepository voltageLevelRepository;
    private final BayRepository bayRepository;
    private final PowerTransformerRepository powerTransformerRepository;

    @Transactional
    public void importFromJson(MultipartFile file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(file.getInputStream());

        for (JsonNode node : root) {
            // Validate required input
            Assert.notNull(!node.hasNonNull("mrid"), "Missing mrid, skipping record: " + node);
            Assert.notNull(!node.hasNonNull("asset.serial_number"), "Missing serial number, skipping record: " + node);
            Assert.notNull(!node.hasNonNull("bay → identified_object.name"), "Missing bay name, skipping record: " + node);

            // LifecycleDate
            String manufacturingYear = node.get("lifecycleDate.manufacturing_year").asText(null);

            LifecycleDate lifecycleDate;
            if (manufacturingYear != null) {
                lifecycleDate = new LifecycleDate();
                lifecycleDate.setMrid(UUID.randomUUID());
                lifecycleDate.setManufacturedDate(manufacturingYear);
                lifecycleDate = lifecycleDateRepository.save(lifecycleDate);
            } else {
                lifecycleDate = null;
            }

            // Manufacturer
            String manufacturerName = node.get("productAssetModel.manufacturer").asText(null);

            Manufacturer manufacturer;
            if (manufacturerName != null) { // check if manufacturer exists
                manufacturer = manufacturerRepository.findByName(manufacturerName)
                        .orElseGet(() -> {
                           Manufacturer m = new Manufacturer();
                           m.setMrid(UUID.randomUUID());
                           m.setName(manufacturerName);
                           return manufacturerRepository.save(m);
                        });
            } else {
                manufacturer = null;
            }

            // Product asset model
            String countryOfOrigin = node.get("asset.country_of_origin").asText(null);

            ProductAssetModel productAssetModel;
            if (countryOfOrigin != null || manufacturer != null) {
                productAssetModel = new ProductAssetModel();
                productAssetModel.setMrid(UUID.randomUUID());
                productAssetModel.setManufacturer(manufacturer);
                productAssetModel.setCountryOfOrigin(countryOfOrigin);
                productAssetModel = productAssetModelRepository.save(productAssetModel);
            } else {
                productAssetModel = null;
            }

            // Frequency
            String frequencyValue = node.get("old_power_transformer_info.rated_frequency").asText(null);
            String frequencyUnit = node.get("Rated frequency_unit").asText(null);

            Frequency frequency;
            if (frequencyValue != null || frequencyUnit != null) { // check if frequency exists
                frequency = frequencyRepository.findByValueAndUnit(frequencyValue, frequencyUnit)
                        .orElseGet(() -> {
                            Frequency f = new Frequency();
                            f.setMrid(UUID.randomUUID());
                            f.setValue(frequencyValue);
                            f.setUnit(frequencyUnit);
                            return frequencyRepository.save(f);
                        });
            } else {
                frequency = null;
            }

            // Old power transformer info
            UUID optiMrid = UUID.fromString(node.get("mrid").asText());
            String phases = node.get("old_power_transformer_info.Phases").asText(null);
            String group = node.get("old_power_ransformer_info.vector group").asText(null);

            ProductAssetModel finalProductAssetModel = productAssetModel;
            OldPowerTransformerInfo oldPowerTransformerInfo = oldPowerTransformerInfoRepository.findByMrid(optiMrid) // check if old power transformer info exists
                            .orElseGet(() -> {
                                OldPowerTransformerInfo o = new OldPowerTransformerInfo();
                                o.setMrid(optiMrid);
                                o.setPhases(phases);
                                o.setVectorGroup(group);
                                o.setRatedFrequency(frequency);
                                o.setProductAssetModel(finalProductAssetModel);
                                return oldPowerTransformerInfoRepository.save(o);
                            });


            // Voltage
            Double value = node.get("voltage_level.base_voltage").asDouble();
            String multiplier = node.get("voltage.multiplier").asText(null);
            String unit = node.get("voltage.unit").asText(null);

            Voltage voltage = voltageRepository.findByValueAndMultiplierAndUnit(value, multiplier, unit) // check if voltage exists
                    .orElseGet(() -> {
                        Voltage v = new Voltage();
                        v.setMrid(UUID.randomUUID());
                        v.setValue(value);
                        v.setMultiplier(multiplier);
                        v.setUnit(unit);
                        return voltageRepository.save(v);
                    });

            // Base voltage
            BaseVoltage baseVoltage = new BaseVoltage();
            baseVoltage.setMrid(UUID.randomUUID());
            baseVoltage.setNominalVoltage(voltage);
            baseVoltage = baseVoltageRepository.save(baseVoltage);

            // Voltage level
            VoltageLevel voltageLevel = new VoltageLevel();
            voltageLevel.setMrid(UUID.randomUUID());
            voltageLevel.setName(value + multiplier + unit);
            voltageLevel.setBaseVoltage(baseVoltage);
            voltageLevel = voltageLevelRepository.save(voltageLevel);

            // Bay
            String bayName = node.get("bay → identified_object.name").asText(null);

            Bay bay;
            VoltageLevel finalVoltageLevel = voltageLevel;
            bay = bayRepository.findByName(bayName)
                    .orElseGet(() -> {
                        Bay b = new Bay();
                        b.setMrid(UUID.randomUUID());
                        b.setName(bayName);
                        b.setVoltageLevel(finalVoltageLevel);
                        return bayRepository.save(b);
                    });

            // Power transformer
            String powerTransformerName = node.get("asset.serial_number").asText(null);

            PowerTransformer powerTransformer = powerTransformerRepository.findByName(powerTransformerName) // check if power transformer exists
                            .orElseGet(() -> {
                                PowerTransformer p = new PowerTransformer();
                                p.setMrid(UUID.randomUUID());
                                p.setName(powerTransformerName);
                                p.setEquipmentContainer(bay);
                                return powerTransformerRepository.save(p);
                            });

            // Asset
            String assetType = node.get("asset.type").asText(null);
            String assetSerialNumber = node.get("asset.serial_number").asText(null);

            LifecycleDate finalLifecycleDate = lifecycleDate;
            assetRepository.findBySerialNumber(assetSerialNumber)
                    .orElseGet(() -> {
                        Asset a = new Asset();
                        a.setName(assetSerialNumber);
                        a.setMrid(UUID.randomUUID());
                        a.setLifecycleDate(finalLifecycleDate);
                        a.setType(assetType);
                        a.setSerialNumber(assetSerialNumber);
                        a.setCountryOfOrigin(countryOfOrigin);
                        a.setAssetInfo(oldPowerTransformerInfo);
                        a.setKind(AssetKind.Transformer);
                        a.setAssetContainer(powerTransformer);
                        return assetRepository.save(a);
                    });
        }

        log.info("JSON import completed.");
    }
}
