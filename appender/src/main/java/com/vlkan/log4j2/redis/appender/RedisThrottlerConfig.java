package com.vlkan.log4j2.redis.appender;

import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;

import static com.vlkan.log4j2.redis.appender.Helpers.requireArgument;
import static org.apache.logging.log4j.util.Strings.isBlank;

@Plugin(name = "RedisThrottlerConfig",
        category = Node.CATEGORY,
        printObject = true)
public class RedisThrottlerConfig {

    private final int bufferSize;

    private final int batchSize;

    private final long flushPeriodMillis;

    private final double maxEventCountPerSecond;

    private final double maxByteCountPerSecond;

    private final String jmxBeanName;

    private RedisThrottlerConfig(Builder builder) {
        this.bufferSize = builder.bufferSize;
        this.batchSize = builder.batchSize;
        this.flushPeriodMillis = builder.flushPeriodMillis;
        this.maxEventCountPerSecond = builder.maxEventCountPerSecond;
        this.maxByteCountPerSecond = builder.maxByteCountPerSecond;
        this.jmxBeanName = isBlank(builder.jmxBeanName) ? null : builder.jmxBeanName;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public long getFlushPeriodMillis() {
        return flushPeriodMillis;
    }

    public double getMaxEventCountPerSecond() {
        return maxEventCountPerSecond;
    }

    public double getMaxByteCountPerSecond() {
        return maxByteCountPerSecond;
    }

    public String getJmxBeanName() {
        return jmxBeanName;
    }

    @Override
    public String toString() {
        return "RedisThrottlerConfig{bufferSize=" + bufferSize +
                ", batchSize=" + batchSize +
                ", flushPeriodMillis=" + flushPeriodMillis +
                ", maxEventCountPerSecond=" + maxEventCountPerSecond +
                ", maxByteCountPerSecond=" + maxByteCountPerSecond +
                ", jmxBeanName=" + jmxBeanName +
                '}';
    }

    @PluginBuilderFactory
    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder implements org.apache.logging.log4j.core.util.Builder<RedisThrottlerConfig> {

        @PluginBuilderAttribute
        private int bufferSize = 500;

        @PluginBuilderAttribute
        private int batchSize = 100;

        @PluginBuilderAttribute
        private long flushPeriodMillis = 1000;

        @PluginBuilderAttribute
        private double maxEventCountPerSecond = 0;

        @PluginBuilderAttribute
        private double maxByteCountPerSecond = 0;

        @PluginBuilderAttribute
        private String jmxBeanName = null;

        private Builder() {
            // Do nothing.
        }

        public int getBufferSize() {
            return bufferSize;
        }

        public Builder setBufferSize(int bufferSize) {
            this.bufferSize = bufferSize;
            return this;
        }

        public int getBatchSize() {
            return batchSize;
        }

        public Builder setBatchSize(int batchSize) {
            this.batchSize = batchSize;
            return this;
        }

        public long getFlushPeriodMillis() {
            return flushPeriodMillis;
        }

        public Builder setFlushPeriodMillis(long flushPeriodMillis) {
            this.flushPeriodMillis = flushPeriodMillis;
            return this;
        }

        public double getMaxEventCountPerSecond() {
            return maxEventCountPerSecond;
        }

        public Builder setMaxEventCountPerSecond(double maxEventCountPerSecond) {
            this.maxEventCountPerSecond = maxEventCountPerSecond;
            return this;
        }

        public double getMaxByteCountPerSecond() {
            return maxByteCountPerSecond;
        }

        public Builder setMaxByteCountPerSecond(double maxByteCountPerSecond) {
            this.maxByteCountPerSecond = maxByteCountPerSecond;
            return this;
        }

        public String getJmxBeanName() {
            return jmxBeanName;
        }

        public Builder setJmxBeanName(String jmxBeanName) {
            this.jmxBeanName = jmxBeanName;
            return this;
        }

        public RedisThrottlerConfig build() {
            check();
            return new RedisThrottlerConfig(this);
        }

        private void check() {
            requireArgument(bufferSize > 0, "expecting: bufferSize > 0, found: %s", bufferSize);
            requireArgument(
                    batchSize > 0 && batchSize < bufferSize,
                    "expecting: batchSize > 0 && batchSize < bufferSize, found: %s",
                    batchSize);
            requireArgument(
                    flushPeriodMillis > 0,
                    "expecting: flushPeriodMillis > 0, found: %s",
                    flushPeriodMillis);
            requireArgument(
                    maxEventCountPerSecond >= 0,
                    "expecting: maxEventCountPerSecond >= 0, found: %d",
                    maxEventCountPerSecond);
            requireArgument(
                    maxByteCountPerSecond >= 0,
                    "expecting: maxByteCountPerSecond >= 0, found: %d",
                    maxByteCountPerSecond);
        }

        @Override
        public String toString() {
            return "Builder{bufferSize=" + bufferSize +
                    ", batchSize=" + batchSize +
                    ", flushPeriodMillis=" + flushPeriodMillis +
                    ", maxEventCountPerSecond=" + maxEventCountPerSecond +
                    ", maxByteCountPerSecond=" + maxByteCountPerSecond +
                    ", jmxBeanName=" + jmxBeanName +
                    '}';
        }

    }

}
