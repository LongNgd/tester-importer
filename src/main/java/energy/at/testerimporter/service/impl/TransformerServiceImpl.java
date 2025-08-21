package energy.at.testerimporter.service.impl;

import energy.at.testerimporter.domain.model.*;
import energy.at.testerimporter.domain.model.dto.TransformerDTO;
import energy.at.testerimporter.repository.AssetRepository;
import energy.at.testerimporter.service.TransformerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TransformerServiceImpl implements TransformerService {
    private final AssetRepository assetRepository;

    public TransformerServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public List<TransformerDTO> listTransformerInfo() {
        List<TransformerDTO> transformerDTOs = new ArrayList<>();

        List<Asset> assets = assetRepository.findAll();
        for (Asset asset : assets) {
            OldPowerTransformerInfo oldPowerTransformerInfo = (OldPowerTransformerInfo) asset.getAssetInfo();
            Frequency frequency = oldPowerTransformerInfo.getRatedFrequency();
            ProductAssetModel productAssetModel = oldPowerTransformerInfo.getProductAssetModel();
            Manufacturer manufacturer = productAssetModel.getManufacturer();
            LifecycleDate lifecycleDate = asset.getLifecycleDate();
            PowerTransformer powerTransformer = (PowerTransformer) asset.getAssetContainer();
            Bay bay = (Bay) powerTransformer.getEquipmentContainer();
            VoltageLevel voltageLevel = bay.getVoltageLevel();
            BaseVoltage baseVoltage = voltageLevel.getBaseVoltage();
            Voltage voltage = baseVoltage.getNominalVoltage();

            TransformerDTO transformerDTO = TransformerDTO.builder()
                    .powerTransformerName(powerTransformer.getName())
                    .phases(oldPowerTransformerInfo.getPhases())
                    .vectorGroup(oldPowerTransformerInfo.getVectorGroup())
                    .ratedFrequency(frequency.getValue())
                    .frequencyUnit(frequency.getUnit())
                    .type(asset.getType())
                    .serialNumber(asset.getSerialNumber())
                    .manufacturer(manufacturer.getName())
                    .manufacturingYear(lifecycleDate.getManufacturedDate())
                    .countryOfOrigin(asset.getCountryOfOrigin())
                    .voltageLevel(voltageLevel.getName())
                    .baseVoltage(voltage.getValue())
                    .multiplier(voltage.getMultiplier())
                    .unit(voltage.getUnit())
                    .bay(bay.getName())
                    .build();

            transformerDTOs.add(transformerDTO);
        }

        return transformerDTOs;
    }
}
