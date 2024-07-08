module gestion.equipos {
    requires jakarta.persistence;
    requires static lombok;
    requires spring.data.jpa;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.context;
    requires spring.beans;
    requires spring.web;
    requires spring.security.core;
    requires spring.security.web;
    requires spring.security.config;
    requires jjwt;
    requires org.apache.tomcat.embed.core;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires jakarta.annotation;
    requires jakarta.servlet;
    requires spring.security.crypto;

    exports org.example.equipos.model;
    exports org.example.equipos.repository;
    exports org.example.equipos.service;
    exports org.example.equipos.security;

    opens org.example.equipos.service to spring.core, org.mockito;
}