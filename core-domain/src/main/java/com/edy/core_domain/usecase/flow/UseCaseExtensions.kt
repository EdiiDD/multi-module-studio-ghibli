package com.edy.core_domain.usecase.flow

fun <R> FlowUseCase<Unit, R>.execute() = execute(Unit)
