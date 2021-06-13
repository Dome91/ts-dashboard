package com.github.dome91.ts3dashboard.core.exceptions

import com.github.dome91.ts3dashboard.core.factories.ID
import java.lang.RuntimeException

class TeamSpeakServerNotFoundException(message: String) : RuntimeException(message) {

    companion object {
        fun byID(id: ID) = TeamSpeakServerNotFoundException("TeamSpeakServer with ID $id not found.")
    }

}
