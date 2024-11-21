package io.nhaiden.linkkistl

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
    fromApplication<LinkkistlApplication>().with(TestcontainersConfiguration::class).run(*args)
}
