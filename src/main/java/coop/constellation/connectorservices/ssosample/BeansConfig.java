package coop.constellation.connectorservices.ssosample;

import com.xtensifi.connectorservices.common.logging.ConnectorLogging;
import com.xtensifi.connectorservices.common.workflow.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


import java.util.Map;


import coop.constellation.connectorservices.ssosample.controller.BaseParamsSupplier;
import coop.constellation.connectorservices.ssosample.helpers.EnhancedConnectorLogging;
import coop.constellation.connectorservices.ssosample.helpers.StdoutConnectorLogging;


@Configuration
public class BeansConfig {

    @Bean
    @Profile("!local")
    ConnectorLogging connectorLogging() {
        return new EnhancedConnectorLogging();
    }

    @Bean
    @Profile("local")
    ConnectorLogging localConnectorLogging() {
        return new StdoutConnectorLogging();
    }

    /**
     * Set up extra params that this connector should use as a base for every
     * request.
     */
    @Bean
    BaseParamsSupplier baseParamsSupplier() {
        return () -> Map.of("localCpConnectionInitSql", "SET TIME ZONE 'UTC';");
    }

    @Bean
    ConnectorConfig connectorConfig() {
        return new ConnectorConfig();
    }

}