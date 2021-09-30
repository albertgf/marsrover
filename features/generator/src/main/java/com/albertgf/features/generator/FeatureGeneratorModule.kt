package com.albertgf.features.generator

import com.albertgf.common.domain.repository.SendRepository
import com.albertgf.core.Rover
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureUsersGenerator = module {

    viewModel { GeneratorViewModel(SendRepository(Rover())) }
}