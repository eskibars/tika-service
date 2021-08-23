package service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import common.JsonPropertyAccessView;
import lombok.Data;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import service.controller.TikaServiceConfig;
import tika.legacy.LegacyPdfProcessorConfig;
import tika.model.TikaPackageInformation;
import tika.processor.CompositeTikaProcessorConfig;


/**
 * All the information about Tika Service configuration
 */
@Data
@Configuration
@ComponentScan({"tika.legacy", "tika.processor"})
public class ServiceInformation {

    @JsonProperty("legacy_processor_config")
    @JsonView(JsonPropertyAccessView.Public.class)
    final
    LegacyPdfProcessorConfig legacyProcessorConfig;

    @JsonProperty("composite_processor_config")
    @JsonView(JsonPropertyAccessView.Public.class)
    final
    CompositeTikaProcessorConfig compositeProcessorConfig;

    @JsonProperty("service_config")
    @JsonView(JsonPropertyAccessView.Public.class)
    final
    TikaServiceConfig serviceConfig;

    @JsonProperty("tika_info")
    @JsonView(JsonPropertyAccessView.Public.class)
    TikaPackageInformation tikaInfo = new TikaPackageInformation();

    public ServiceInformation(LegacyPdfProcessorConfig legacyProcessorConfig, CompositeTikaProcessorConfig compositeProcessorConfig, TikaServiceConfig serviceConfig) {
        this.legacyProcessorConfig = legacyProcessorConfig;
        this.compositeProcessorConfig = compositeProcessorConfig;
        this.serviceConfig = serviceConfig;
    }
}
