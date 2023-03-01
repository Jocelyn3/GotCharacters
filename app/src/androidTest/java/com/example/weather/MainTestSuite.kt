package com.example.weather

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    ApiTest::class,
    LocalDbTest::class
)
class MainTestSuite {
}