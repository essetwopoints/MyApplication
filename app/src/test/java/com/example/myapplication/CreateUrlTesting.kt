package com.example.myapplication

import org.junit.Test
import junit.framework.Assert

class CreateUrlTesting {
    @Test
    fun createUrl() {

                Assert.assertEquals("http://api.github.com/repos/containers/image/stargazers", CreateUrl().createUrl("containers", "image"))
            }
        }

