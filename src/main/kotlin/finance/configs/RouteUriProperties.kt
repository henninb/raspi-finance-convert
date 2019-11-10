package finance.configs

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import javax.validation.constraints.NotNull

@Component
@ConfigurationProperties(prefix = "route", ignoreUnknownFields = false)
open class RouteUriProperties {

    @NotNull
    lateinit var autoStartRoute: String

    @NotNull
    lateinit var jsonFilesInputPath: String

    @NotNull
    lateinit var jsonFileReaderRoute: String
}
