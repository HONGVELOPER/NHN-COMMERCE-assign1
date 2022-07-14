package com.nhn.assign.service

import com.nhn.assign.dto.PostDto
import com.nhn.assign.model.Product
import com.nhn.assign.repository.ProductRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ProductService(
    private val productRepository: ProductRepository,
) {
    fun findProductList(): List<Product> {
        return productRepository.findProductList()
    }

    fun findDetailProduct(id: Int): Product {
        return productRepository.findDetailProduct(id)
    }

    fun insertProduct(postDto: PostDto) {
        productRepository.insertProduct(postDto.name, postDto.price?:0, LocalDateTime.now(), LocalDateTime.now())
    }

    fun updateProduct(id: Int, postDto: PostDto) {
        println("update service 진입")
        productRepository.updateProduct(id, postDto.name, postDto.price?:0, LocalDateTime.now())
    }

    fun deleteProduct(id: Int) {
        productRepository.deleteProduct(id)
    }
}
