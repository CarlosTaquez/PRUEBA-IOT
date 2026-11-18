# Dashboard IoT

En Vercel, importa el repositorio y selecciona `dashboard` como **Root Directory**. El archivo `vercel.json` configura el sitio estático, con el preset **Other** y sin paso de compilación.

Para usar el backend remoto, configura las URLs en `config.js` y vuelve a desplegar el dashboard.

| Campo | Ejemplo |
| --- | --- |
| `historyApi` | `https://iot-api.example.com` |
| `decisionApi` | `https://iot-api.example.com/decide` |
| `plantSocket` | `wss://iot-api.example.com/ws/plant` |
| `operatorSocket` | `wss://iot-api.example.com/ws/operator` |

El servidor del backend debe exponer las rutas HTTPS y WebSocket. Si el servicio de decisiones está en otro dominio, configura `DASHBOARD_ORIGIN` con el origen HTTPS exacto del dashboard, por ejemplo `https://prueba-iot.vercel.app`. `config.js` es visible en el navegador y solo debe contener URLs.

Con la configuración vacía, el dashboard local usa los puertos `8081`, `8082`, `8083` y `8090`. El dashboard alojado indica que la conexión a la planta está pendiente de configuración.
