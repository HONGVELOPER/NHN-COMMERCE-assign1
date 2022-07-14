package com.nhn.assign.controller

import com.nhn.assign.dto.PostDto
import com.nhn.assign.model.Product
import com.nhn.assign.service.ProductService
import org.apache.ibatis.annotations.Delete
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class ProductController(
    private val productService: ProductService,
) {
    @GetMapping("/product")
    fun getProductList(model: Model): String {
        println("get all product")
        model.addAttribute("productList", productService.findProductList())
        return "product"
    }

    @GetMapping("/detailProduct/{id}")
    fun getProductDetail(@PathVariable(name="id") id: Int, model: Model): String {
        println("get detail product id : $id")
        model.addAttribute("detailProduct", productService.findDetailProduct(id))
        return "detailProduct"
    }

    @GetMapping("/productPostForm")
    fun getPostProductForm(model: Model): String {
        println("route to post form")
        model.addAttribute("postDto", PostDto("", 0))
        return "create"
    }

    @PostMapping("/productPost")
    fun postProduct(postDto: PostDto): String {
        println("post product")
        fun Int.checkPrice() {
            if (postDto.price != null) {
                if (postDto.price < 0) {
                    println("에러")
                    throw Exception("가격이 0보다 같거나 작습니다.")
                }
            }
        }
        postDto.let {
            it.price?.checkPrice()
        }
        productService.insertProduct(postDto)
        return "redirect:/product"
    }

    @GetMapping("/productUpdateForm/{id}")
    fun getUpdateProductForm(@PathVariable(name="id") id: Int, model: Model): String {
        println("route to update form")
        val product = productService.findDetailProduct(id)
        val postDto = PostDto(product.productName, product.salePrice)
        model.addAttribute("id", id)
        model.addAttribute("postDto", postDto)
        return "update"
    }

    // TODO (상품 수정 기능 + Exception 처리)
    @PostMapping("/productPut/{id}")
    fun updateProduct(@PathVariable(name="id") id: Int, postDto: PostDto): String {
        println("update product")
        productService.updateProduct(id, postDto)
        return "redirect:/detailProduct/$id"
    }

    // TODO (상품 삭제 기능 + Exception 처리)제
    @GetMapping("/deleteProduct/{id}")
    fun deleteProduct(@PathVariable(name="id") id: Int) :String {
        println("delete product")
        productService.deleteProduct(id)
        return "redirect:/product"
    }
}
