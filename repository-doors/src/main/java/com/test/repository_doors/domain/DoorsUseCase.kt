package com.test.repository_doors.domain

class DoorsUseCase(
    private val doorsRepository: DoorsRepository
) {
    val doorsFromDb = doorsRepository.getDoorsFromDb()
    val doorsFromNetwork = doorsRepository.getDoorsNetwork()

    fun updateDoor(door: DoorsInfo) {
        doorsRepository.updateDoor(door)
    }

    fun saveDoorFromNetwork(list: List<DoorsInfo>) {
        doorsRepository.saveDoors(list)
    }

    fun saveDoors() {
        doorsRepository.saveDoorsFromNetwork()
    }
}
