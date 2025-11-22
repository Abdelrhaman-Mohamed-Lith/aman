//package com.amandelivery.backend.config;
//
//import org.hibernate.boot.MetadataBuilder;
//import org.hibernate.boot.registry.StandardServiceRegistry;
//import org.hibernate.boot.spi.MetadataBuilderInitializer;
//import org.hibernate.type.AbstractStandardBasicType;
//import org.hibernate.type.descriptor.java.StringJavaType;
//import org.hibernate.type.descriptor.jdbc.NVarcharJdbcType;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class GlobalTypeInitializer implements MetadataBuilderInitializer {
//
//
//    @Override
//    public void contribute(MetadataBuilder metadataBuilder, StandardServiceRegistry standardServiceRegistry) {
//        // Register nationalized string type
//        metadataBuilder.applyBasicType(NationalizedStringType.INSTANCE);
//
//        // Configure enum storage as string globally
//        metadataBuilder.applyBasicType(new org.hibernate.type.EnumType<>());
//    }
//
//    public static class NationalizedStringType extends AbstractStandardBasicType<String> {
//        public static final NationalizedStringType INSTANCE = new NationalizedStringType();
//
//        public NationalizedStringType() {
//            super(NVarcharJdbcType.INSTANCE, StringJavaType.INSTANCE);
//        }
//
//        @Override
//        public String getName() {
//            return "nstring";
//        }
//    }
//}