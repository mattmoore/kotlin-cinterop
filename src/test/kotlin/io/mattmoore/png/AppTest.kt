package io.mattmoore.png

import libpng.*

import kotlin.test.Test
import kotlin.test.assertNotNull

class AppTest {
    @Test fun `png returns a version`() {
        assertNotNull(png_access_version_number(), "app should have a greeting")
    }
}
