package orion.catalog.catalog_bff.infrastructure.config

import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.cache.CacheManager
import org.springframework.context.annotation.Configuration
import orion.catalog.catalog_bff.application.shared.BaseUsecaseInterface
import orion.catalog.catalog_bff.application.shared.annotation.Usecase
import orion.catalog.catalog_bff.infrastructure.executors.CacheUsecaseResultExecutorImpl
import java.lang.reflect.Proxy

@Configuration
class UsecaseConfigBeanProcessor(
    private val cacheManager: CacheManager
): BeanPostProcessor {

    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
        val usecaseCaseAnnotation: Usecase? = bean.javaClass.getAnnotation(Usecase::class.java)

        if (bean is BaseUsecaseInterface<*, *> && usecaseCaseAnnotation != null) {
            var decoratedUsecase: BaseUsecaseInterface<Any, Any> = bean as BaseUsecaseInterface<Any, Any>
            if (usecaseCaseAnnotation.cache) {
                decoratedUsecase = CacheUsecaseResultExecutorImpl(cacheManager, bean)
            }

            // proxy implements both the specialized usecase interface AND BaseUsecaseInterface
            return Proxy.newProxyInstance(
                bean::class.java.classLoader,
                bean::class.java.interfaces,   // preserve all interfaces
            ) { _, method, args ->
                method.invoke(decoratedUsecase, *(args ?: emptyArray()))
            }
        }

        return bean
    }
}