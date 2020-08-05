/*
 * Copyright 2020 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ai.djl.repository.zoo;

import ai.djl.Application;
import ai.djl.Device;
import ai.djl.nn.Block;
import ai.djl.translate.Translator;
import ai.djl.util.Progress;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code Criteria} class contains search criteria to look up a {@link ZooModel}.
 *
 * @param <I> the model input type
 * @param <O> the model output type
 */
public class Criteria<I, O> {

    private Application application;
    private Class<I> inputClass;
    private Class<O> outputClass;
    private String engine;
    private Device device;
    private String groupId;
    private String artifactId;
    private ModelZoo modelZoo;
    private Map<String, String> filters;
    private Map<String, Object> arguments;
    private Map<String, Object> options;
    private Translator<I, O> translator;
    private Block block;
    private String modelName;
    private Progress progress;

    Criteria(Builder<I, O> builder) {
        this.application = builder.application;
        this.inputClass = builder.inputClass;
        this.outputClass = builder.outputClass;
        this.engine = builder.engine;
        this.device = builder.device;
        this.groupId = builder.groupId;
        this.artifactId = builder.artifactId;
        this.modelZoo = builder.modelZoo;
        this.filters = builder.filters;
        this.arguments = builder.arguments;
        this.options = builder.options;
        this.translator = builder.translator;
        this.block = builder.block;
        this.modelName = builder.modelName;
        this.progress = builder.progress;
    }

    /**
     * Returns the application of the model.
     *
     * @return the application of the model
     */
    public Application getApplication() {
        return application;
    }

    /**
     * Returns the input data type.
     *
     * @return the input data type
     */
    public Class<I> getInputClass() {
        return inputClass;
    }

    /**
     * Returns the output data type.
     *
     * @return the output data type
     */
    public Class<O> getOutputClass() {
        return outputClass;
    }

    /**
     * Returns the engine name.
     *
     * @return the engine name
     */
    public String getEngine() {
        return engine;
    }

    /**
     * Returns the {@link Device} of the model to be loaded on.
     *
     * @return the {@link Device} of the model to be loaded on
     */
    public Device getDevice() {
        return device;
    }

    /**
     * Returns the groupId of the {@link ModelZoo} to be searched.
     *
     * @return the groupId of the {@link ModelZoo} to be searched
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * Returns the artifactId of the {@link ModelLoader} to be searched.
     *
     * @return the artifactIds of the {@link ModelLoader} to be searched
     */
    public String getArtifactId() {
        return artifactId;
    }

    /**
     * Returns the {@link ModelZoo} to be searched.
     *
     * @return the {@link ModelZoo} to be searched
     */
    public ModelZoo getModelZoo() {
        return modelZoo;
    }

    /**
     * Returns the search filters that must match the properties of the model.
     *
     * @return the search filters that must match the properties of the model.
     */
    public Map<String, String> getFilters() {
        return filters;
    }

    /**
     * Returns the override configurations of the model loading arguments.
     *
     * @return the override configurations of the model loading arguments
     */
    public Map<String, Object> getArguments() {
        return arguments;
    }

    /**
     * Returns the model loading options.
     *
     * @return the model loading options
     */
    public Map<String, Object> getOptions() {
        return options;
    }

    /**
     * Returns the optional {@link Translator} to be used for {@link ZooModel}.
     *
     * @return the optional {@link Translator} to be used for {@link ZooModel}
     */
    public Translator<I, O> getTranslator() {
        return translator;
    }

    /**
     * Returns the optional {@link Block} to be used for {@link ZooModel}.
     *
     * @return the optional {@link Block} to be used for {@link ZooModel}
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Returns the optional model name to be used for {@link ZooModel}.
     *
     * @return the optional model name to be used for {@link ZooModel}
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * Returns the optional {@link Progress} for the model loading.
     *
     * @return the optional {@link Progress} for the model loading
     */
    public Progress getProgress() {
        return progress;
    }

    /**
     * Creates a builder to build a {@code Criteria}.
     *
     * @return a new builder
     */
    public static Builder<?, ?> builder() {
        return new Builder<>();
    }

    /** A Builder to construct a {@code Criteria}. */
    public static final class Builder<I, O> {

        Application application;
        Class<I> inputClass;
        Class<O> outputClass;
        String engine;
        Device device;
        String groupId;
        String artifactId;
        ModelZoo modelZoo;
        Map<String, String> filters;
        Map<String, Object> arguments;
        Map<String, Object> options;
        Translator<I, O> translator;
        Block block;
        String modelName;
        Progress progress;

        Builder() {}

        private Builder(Class<I> inputClass, Class<O> outputClass, Builder<?, ?> parent) {
            this.inputClass = inputClass;
            this.outputClass = outputClass;
            application = parent.application;
            engine = parent.engine;
            device = parent.device;
            groupId = parent.groupId;
            filters = parent.filters;
            arguments = parent.arguments;
            options = parent.options;
            block = parent.block;
            modelName = parent.modelName;
            progress = parent.progress;
        }

        /**
         * Creates a new @{code Builder} class with the specified input and output data type.
         *
         * @param <P> the input data type
         * @param <Q> the output data type
         * @param inputClass the input class
         * @param outputClass the output class
         * @return a new @{code Builder} class with the specified input and output data type
         */
        public <P, Q> Builder<P, Q> setTypes(Class<P> inputClass, Class<Q> outputClass) {
            return new Builder<>(inputClass, outputClass, this);
        }

        /**
         * Sets the model application for this criteria.
         *
         * @param application the model application
         * @return this {@code Builder}
         */
        public Builder<I, O> optApplication(Application application) {
            this.application = application;
            return this;
        }

        /**
         * Sets the engine name for this criteria.
         *
         * @param engine the engine name
         * @return this {@code Builder}
         */
        public Builder<I, O> optEngine(String engine) {
            this.engine = engine;
            return this;
        }

        /**
         * Sets the {@link Device} for this criteria.
         *
         * @param device the {@link Device} for the criteria
         * @return this {@code Builder}
         */
        public Builder<I, O> optDevice(Device device) {
            this.device = device;
            return this;
        }

        /**
         * Sets optional groupId of the {@link ModelZoo} for this criteria.
         *
         * @param groupId the groupId of the {@link ModelZoo}
         * @return this {@code Builder}
         */
        public Builder<I, O> optGroupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        /**
         * Sets optional artifactId of the {@link ModelLoader} for this criteria.
         *
         * @param artifactId the artifactId of the {@link ModelLoader}
         * @return this {@code Builder}
         */
        public Builder<I, O> optArtifactId(String artifactId) {
            if (artifactId.contains(":")) {
                String[] tokens = artifactId.split(":");
                groupId = tokens[0];
                this.artifactId = tokens[1];
            } else {
                this.artifactId = artifactId;
            }
            return this;
        }

        /**
         * Sets optional model urls of the {@link ModelLoader} for this criteria.
         *
         * @param modelUrls the comma delimited url string
         * @return this {@code Builder}
         */
        public Builder<I, O> optModelUrls(String modelUrls) {
            this.modelZoo = new DefaultModelZoo(modelUrls);
            return this;
        }

        /**
         * Sets optional {@link ModelZoo} of the {@link ModelLoader} for this criteria.
         *
         * @param modelZoo ModelZoo} of the {@link ModelLoader} for this criteria
         * @return this {@code Builder}
         */
        public Builder<I, O> optModelZoo(ModelZoo modelZoo) {
            this.modelZoo = modelZoo;
            return this;
        }

        /**
         * Sets the extra search filters for this criteria.
         *
         * @param filters the extra search filters
         * @return this {@code Builder}
         */
        public Builder<I, O> optFilters(Map<String, String> filters) {
            this.filters = filters;
            return this;
        }

        /**
         * Sets an extra search filter for this criteria.
         *
         * @param key the search key
         * @param value the search value
         * @return this {@code Builder}
         */
        public Builder<I, O> optFilter(String key, String value) {
            if (filters == null) {
                filters = new HashMap<>();
            }
            filters.put(key, value);
            return this;
        }

        /**
         * Sets an optional model {@link Block} for this criteria.
         *
         * @param block optional model {@link Block} for this criteria
         * @return this {@code Builder}
         */
        public Builder<I, O> optBlock(Block block) {
            this.block = block;
            return this;
        }

        /**
         * Sets an optional model name for this criteria.
         *
         * @param modelName optional model name for this criteria
         * @return this {@code Builder}
         */
        public Builder<I, O> optModelName(String modelName) {
            this.modelName = modelName;
            return this;
        }

        /**
         * Sets an extra model loading argument for this criteria.
         *
         * @param arguments optional model loading arguments
         * @return this {@code Builder}
         */
        public Builder<I, O> optArguments(Map<String, Object> arguments) {
            this.arguments = arguments;
            return this;
        }

        /**
         * Sets the optional model loading argument for this criteria.
         *
         * @param key the model loading argument key
         * @param value the model loading argument value
         * @return this {@code Builder}
         */
        public Builder<I, O> optArgument(String key, Object value) {
            if (arguments == null) {
                arguments = new HashMap<>();
            }
            arguments.put(key, value);
            return this;
        }

        /**
         * Sets the model loading options for this criteria.
         *
         * @param options the model loading options
         * @return this {@code Builder}
         */
        public Builder<I, O> optOptions(Map<String, Object> options) {
            this.options = options;
            return this;
        }

        /**
         * Sets the optional model loading option for this criteria.
         *
         * @param key the model loading option key
         * @param value the model loading option value
         * @return this {@code Builder}
         */
        public Builder<I, O> optOption(String key, Object value) {
            if (options == null) {
                options = new HashMap<>();
            }
            options.put(key, value);
            return this;
        }

        /**
         * Sets the optional {@link Translator} to override default {@code Translator}.
         *
         * @param translator the override {@code Translator}
         * @return this {@code Builder}
         */
        public Builder<I, O> optTranslator(Translator<I, O> translator) {
            this.translator = translator;
            return this;
        }

        /**
         * Set the optional {@link Progress}.
         *
         * @param progress the {@code Progress}
         * @return this {@code Builder}
         */
        public Builder<I, O> optProgress(Progress progress) {
            this.progress = progress;
            return this;
        }

        /**
         * Builds a {@link Criteria} instance.
         *
         * @return the {@link Criteria} instance
         */
        public Criteria<I, O> build() {
            if (inputClass == null || outputClass == null) {
                throw new IllegalArgumentException(
                        "Input and output type are required for a Criteria.");
            }
            return new Criteria<>(this);
        }
    }
}
