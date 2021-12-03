
package extensions

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * Adds a dependency to the `testImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.testImplementation(dependencyNotation: String): Dependency? =
    add("testImplementation", dependencyNotation)

/**
 * Adds a dependency to the `androidTestImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.androidTestImplementation(dependencyNotation: String): Dependency? =
    add("androidTestImplementation", dependencyNotation)

/**
 * Adds all the tests dependencies to specific configuration.
 */
fun DependencyHandler.addTestsDependencies() {
    testImplementation(TestDependencies.Junit.junit)
    testImplementation(TestDependencies.Mock.mockk)
    testImplementation(TestDependencies.Coroutines.coroutines)

    testImplementation(TestDependencies.Junit.koin)
    testImplementation(TestDependencies.Koin.koin)
    testImplementation(TestDependencies.AndroidArch.androidArch)
    testImplementation(TestDependencies.Ktor.ktor)
}
