package com.albertgf.common.domain

import com.albertgf.common.domain.models.RoverDomain

class DomainTestUtil {

    companion object {
        fun fakeRoverDomain(): RoverDomain {
            return RoverDomain()
        }

        fun fakeRoverDataParserString(): String {
            return "{\"topRightCorner\":{\"x\":0,\"y\":0},\"roverPosition\":{\"x\":0,\"y\":0},\"roverDirection\":\"\",\"movements\":\"\"}"
        }
    }
}