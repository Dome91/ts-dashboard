package com.github.dome91.ts3dashboard.application.common

import org.mapstruct.MapperConfig
import org.mapstruct.ReportingPolicy

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR)
interface MapperConfiguration
