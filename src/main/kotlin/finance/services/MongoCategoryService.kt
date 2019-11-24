package finance.services

import finance.models.Category
import finance.repositories.MongoCategoryRepository
import finance.utils.Constants.METRIC_DUPLICATE_CATEGORY_INSERT_ATTEMPT_COUNTER
import io.micrometer.core.instrument.MeterRegistry
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import java.util.Optional
import java.util.Optional.empty

@Profile("mongo")
@Service
open class MongoCategoryService @Autowired constructor(
        private var categoryRepository: MongoCategoryRepository<Category>,
        private var meterRegistry: MeterRegistry
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    //@Timed("find.by.category.timer")
    fun findByCategory( category: String ): Optional<Category> {
        logger.debug("findByCategory")
        val categoryOptional: Optional<Category> = categoryRepository.findByCategory(category)
        if( categoryOptional.isPresent ) {
            return categoryOptional
        }
        return empty()
    }

    //TODO: which @Transactional is the one to utilize
    //@Timed("insert.category.timer")
    fun insertCategory(category: Category) : Boolean {
        logger.debug("insertAccount")

        try {
            categoryRepository.save(category)
        } catch ( e: JdbcSQLIntegrityConstraintViolationException) {
            meterRegistry.counter(METRIC_DUPLICATE_CATEGORY_INSERT_ATTEMPT_COUNTER).increment()
            logger.info("categoryRepository.saveAndFlush(category) - JdbcSQLIntegrityConstraintViolationException")
            return false
        }

        return true
    }
}