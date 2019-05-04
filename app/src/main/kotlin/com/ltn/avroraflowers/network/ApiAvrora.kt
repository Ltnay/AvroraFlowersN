package com.ltn.avroraflowers.network

import com.ltn.avroraflowers.model.*
import com.ltn.avroraflowers.network.RequestBody.AddToCart
import com.ltn.avroraflowers.network.RequestBody.UserLogin
import com.ltn.avroraflowers.network.RequestBody.UserRegister
import com.ltn.avroraflowers.network.Response.LoginResponse
import com.ltn.avroraflowers.network.Response.RegisterResponse
import com.ltn.avroraflowers.network.Response.SampleResponse
import io.reactivex.Observable
import retrofit2.http.*

interface ApiAvrora {

    @GET("/categories")
    fun getCategories(
        @Query("order_by") orderBy: String,
        @Query("type") typeSort: String
    ): Observable<List<Category>>

    @GET("/categories")
    fun getCategories(): Observable<List<Category>>

    @GET("/categories/{id}/products")
    fun getProductsByCategoryId(@Path("id") id: Int): Observable<List<Product>>

    @Headers("Content-type: application/json")
    @POST("/login")
    fun userLogin(@Body userLogin: UserLogin): Observable<LoginResponse>

    @Headers("Content-type: application/json")
    @POST("/registration")
    fun userRegister(@Body userRegister: UserRegister): Observable<RegisterResponse>

    @POST("/user/cart")
    fun addProductInCart(@Header("access-token") accessToken: String, @Body addToCart: AddToCart): Observable<SampleResponse>

    @GET("/user/cart")
    fun getProductsInCart(@Header("access-token") accessToken: String): Observable<List<CartItem>>

    @DELETE("/user/cart/{id}")
    fun deleteProductInCart(@Header("access-token") accessToken: String, @Path("id") id: Int): Observable<SampleResponse>

    @GET("/user/orders")
    fun getOrders(@Header("access-token") accessToken: String): Observable<List<OrderItem>>

    @GET("/user/orders/{id}/products")
    fun getProductsInOrderById(@Header("access-token") accessToken: String, @Path("id") id: Int): Observable<List<OrderInner>>
}