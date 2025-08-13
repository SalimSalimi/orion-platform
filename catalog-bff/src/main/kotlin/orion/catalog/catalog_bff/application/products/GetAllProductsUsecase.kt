package orion.catalog.catalog_bff.application.products

import orion.catalog.catalog_bff.application.shared.BaseUsecaseWithoutParameterInterface
import orion.catalog.catalog_bff.domain.product.Product

interface GetAllProductsUsecase: BaseUsecaseWithoutParameterInterface<List<Product>>