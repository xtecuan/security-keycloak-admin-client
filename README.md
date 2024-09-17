# security-keycloak-admin-client

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/security-keycloak-admin-client-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- REST Jackson ([guide](https://quarkus.io/guides/rest#json-serialisation)): Jackson serialization support for Quarkus REST. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it

| Título                                            | Responsable   | Fecha de Inicio | Fecha Final | Estado  | Descripción                                                                                                                                             ||---------------------------------------------------|---------------|-----------------|-------------|---------|---------------------------------------------------------------------------------------------------------------------------------------------------------|| Crear recursos, políticas y scopes en Keycloak    | Julian Rivera | Por definir     | Por definir  | Pendiente | Crear los recursos, políticas, permisos y scopes en Keycloak para POS Virtual, basado en la documentación de arquitectura especificada.                 || Integrar autorización/seguridad en los endpoints  | Julian Rivera | Por definir     | Por definir  | Pendiente | Implementar la autorización y seguridad en todos los endpoints según los requisitos de POS Virtual.                                                    || Validar roles contra permisos                     | Julian Rivera | Por definir     | Por definir  | Pendiente | Implementar validaciones basadas en roles para permisos en los módulos de Cargos Recurrentes y Link de Pago.                                            || Validar rol de usuario para acceso                | Julian Rivera | Por definir     | Por definir  | Pendiente | Asegurarse de que la API de Link de Pago valide que el usuario tenga los roles `recurring_charges_access` y `payment_link_access` para acceder al módulo. || Investigar integración de nuevos módulos          | Julian Rivera | Por definir     | Por definir  | Pendiente | Investigar cómo integrar los nuevos módulos de manera efectiva, teniendo en cuenta que las anotaciones no siempre funcionan en cascada como se espera.  || Unificar modelos de Link de Pago y Cargos Recurrentes | Julian Rivera | Por definir     | Por definir  | Pendiente | Unificar los modelos de transacciones para los módulos de Link de Pago y Cargos Recurrentes.                                                            || Actualizar mapeos del Reverse Proxy               | Julian Rivera | Por definir     | Por definir  | Pendiente | Cambiar los mapeos del reverse proxy para los aplicativos de Cargos Recurrentes y Link de Pago, mapeando los endpoints a las nuevas estructuras de URL especificadas. |
