package com.rivia.software.marvelpoc.domain.usecase

interface BaseUseCase<in Parameter, out Result> {
    suspend operator fun invoke(params: Parameter): Result
}