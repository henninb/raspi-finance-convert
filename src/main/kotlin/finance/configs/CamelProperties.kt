package finance.configs

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "custom.project.camel-route", ignoreUnknownFields = false)
open class CamelProperties(
        var autoStartRoute: String = "",
        var jsonFileReaderRouteId: String = "",
        var jsonFileReaderRoute: String = "",
        var excelFileReaderRouteId: String = "",
        var excelFileReaderRoute: String = "",
        var jsonFileWriterRouteId: String = "",
        var transactionToDatabaseRouteId: String = "",
        var transactionToDatabaseRoute: String = "",
        var jsonFileWriterRoute: String = "",
        var savedFileEndpoint: String = "",
        var failedExcelFileEndpoint: String = "",
        var failedJsonFileEndpoint: String = ""
) {
    constructor() : this(savedFileEndpoint = "")
}
