package com.nhn.assign.repository

import com.nhn.assign.model.Product
import org.apache.ibatis.annotations.*
import java.time.LocalDateTime

@Mapper
interface ProductRepository {
    @Select("SELECT * FROM product")
    fun findProductList(): List<Product>

    @Select("SELECT * FROM product where productNo = #{id}")
    fun findDetailProduct(id: Int): Product

    @Insert("INSERT INTO product(productName, salePrice, registerYmdt, updateYmdt) VALUES(#{name}, #{price}, #{created}, #{updated})")
    fun insertProduct(name: String, price: Int, created: LocalDateTime, updated: LocalDateTime)

    @Update("UPDATE product set productName = #{name}, salePrice = #{salePrice}, updateYmdt = #{updated} WHERE productNo = #{productNo}")
    fun updateProduct(productNo: Int, name: String, salePrice: Int, updated: LocalDateTime)

    @Delete("DELETE FROM product where productNo = #{productNo}")
    fun deleteProduct(productNo: Int)
}
