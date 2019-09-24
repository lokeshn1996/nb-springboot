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
package com.github.alexfalappa.nbspringboot.cfgprops.completion.providers;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.Consumer;

import org.netbeans.api.java.source.ClassIndex;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.springframework.boot.configurationmetadata.ConfigurationMetadataProperty;
import org.springframework.boot.configurationmetadata.ValueHint;

import com.github.alexfalappa.nbspringboot.cfgprops.completion.CfgPropLoggerCompletionItem;

/**
 *
 * @author Alessandro Falappa
 */
public class LoggerNameBootProvider implements BootProvider {

    private final ClassIndex classIndex;

    public LoggerNameBootProvider(ClassIndex classIndex) {
        this.classIndex = classIndex;
    }

    @Override
    public void provideKeys(ConfigurationMetadataProperty propMetadata, String filter, Consumer<ValueHint> consumer) {
        Set<String> packageNames = classIndex.getPackageNames(filter, true, EnumSet.allOf(ClassIndex.SearchScope.class));
        packageNames.stream()
                .map((name) -> {
                    ValueHint vh = new ValueHint();
                    vh.setValue(name);
                    return vh;
                })
                .forEach(consumer);
    }

    @Override
    public void provideValues(ConfigurationMetadataProperty propMetadata, String filter, Consumer<ValueHint> consumer) {
    }

    @Override
    public void provide(ConfigurationMetadataProperty propMetadata, String filter, CompletionResultSet completionResultSet, int dotOffset, int caretOffset) {
        Set<String> packageNames = classIndex.getPackageNames(filter, true, EnumSet.allOf(ClassIndex.SearchScope.class));
        for (String name : packageNames) {
            completionResultSet.addItem(new CfgPropLoggerCompletionItem(name, dotOffset, caretOffset));
        }
    }
    
}