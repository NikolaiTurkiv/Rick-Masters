package com.test.repository_doors.domain

class DoorsUseCase(
   private val doorsRepository: DoorsRepository
) {
    val doors = doorsRepository.getDoors()
}