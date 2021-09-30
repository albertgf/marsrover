package com.albertgf.features.generator

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureUsersGenerator = module {

    viewModel { GeneratorViewModel() }
}