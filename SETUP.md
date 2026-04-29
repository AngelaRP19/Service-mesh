# Service Mesh - MySQL Setup

## Estructura de Bases de Datos

Cada microservicio tiene su propia BD MySQL independiente:

- **Order Service** → Puerto 3306 → BD: `order_db`
- **Customer Service** → Puerto 3307 → BD: `customer_db`  
- **Login Service** → Puerto 3308 → BD: `login_db`

Todas comparten usuario: `root` / contraseña: `uptc2026`

## Pasos para levantar los servicios

### 1. Levantar bases de datos MySQL

```bash
docker-compose up -d
```

Esto levanta 3 contenedores MySQL independientes.

### 2. Verificar que MySQL está listo

```bash
docker-compose ps
docker logs mysql-order
docker logs mysql-customer
docker logs mysql-login
```

### 3. Construir imágenes Docker de los microservicios (opcional, si quieres desplegarlos en Docker)

```bash
docker build -t order-service ./order
docker build -t customer-service ./customer
docker build -t login-service ./login
```

### 4. Para desarrollo local (sin Docker de los servicios)

Puedes correr los servicios directamente con Maven/IDE:

```bash
cd order && mvn spring-boot:run
cd customer && mvn spring-boot:run
cd login && mvn spring-boot:run
```

Las apps se conectarán automáticamente a sus respectivas BDs en MySQL.

## Verificar conectividad

```bash
# Conectarse a cada BD
mysql -h localhost -P 3306 -u root -p uptc2026 -e "use order_db; show tables;"
mysql -h localhost -P 3307 -u root -p uptc2026 -e "use customer_db; show tables;"
mysql -h localhost -P 3308 -u root -p uptc2026 -e "use login_db; show tables;"
```

## Parar los servicios

```bash
docker-compose down
```

Para eliminar volúmenes (datos persistentes):

```bash
docker-compose down -v
```

## Próximos pasos (cuando lo indiques)

- [ ] Configurar Kubernetes manifests
- [ ] Integrar Service Mesh (Istio)
- [ ] Configurar VirtualServices y DestinationRules para inter-microservice communication
