/*
 * Copyright 2019 Alessandro Falappa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.alexfalappa.nbspringboot.projects.service.impl;

import java.util.Map;

import org.netbeans.api.java.source.ClassIndex;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.springframework.boot.configurationmetadata.ConfigurationMetadataProperty;

import com.github.alexfalappa.nbspringboot.projects.service.api.HintProvider;

/**
 * Implementation of {@link HintProvider} for class references.
 *
 * @author Alessandro Falappa
 */
public class ClassReferenceHintProvider implements HintProvider {

    private final ClassIndex classIndex;

    public ClassReferenceHintProvider(ClassIndex classIndex) {
        this.classIndex = classIndex;
    }

    @Override
    public void provide(Map<String, Object> params, ConfigurationMetadataProperty propMetadata, String filter,
            CompletionResultSet completionResultSet, int dotOffset, int caretOffset) {
        if (filter == null) {
            filter = "";
        }
        String baseType = "java.lang.Object";
        boolean concrete = true;
        if (params.containsKey("target")) {
            baseType = params.get("target").toString();
        }
        if (params.containsKey("concrete")) {
            concrete = Boolean.valueOf(params.get("concrete").toString());
        }
        // TODO implement search of classes extending baseType
    }

}