# 🌸 Aromas del Alma - PWA

## ¿Qué es esto?

Esta es una **Progressive Web App (PWA)** del sistema de ventas e inventario para Aromas del Alma. Una PWA funciona como una aplicación nativa pero no necesita descargarse de la App Store.

## ✨ Características

- ✅ **Funciona SIN internet** (después de la primera instalación)
- ✅ **Se instala en el iPad** como una app normal
- ✅ **Base de datos local** (IndexedDB) - tus datos NO se pierden
- ✅ **Escáner de códigos de barras**
- ✅ **Control de inventario**
- ✅ **Sistema de ventas con descuentos**

---

## 📱 Cómo Instalar en iPad

### Opción 1: Hospedar en Línea (RECOMENDADO)

#### Paso 1: Subir a GitHub Pages (GRATIS)

1. Ve a https://github.com y crea una cuenta (si no tienes)
2. Crea un nuevo repositorio llamado `aromas-del-alma`
3. Sube todos estos archivos al repositorio
4. Ve a Settings > Pages
5. En "Source" selecciona "main branch"
6. Espera 1-2 minutos y tu app estará en: `https://TU-USUARIO.github.io/aromas-del-alma`

#### Paso 2: Instalar en el iPad

1. Abre Safari en el iPad
2. Ve a la URL de tu app: `https://TU-USUARIO.github.io/aromas-del-alma`
3. Toca el botón de **Compartir** (cuadro con flecha hacia arriba)
4. Selecciona **"Añadir a pantalla de inicio"**
5. Nombra la app "Aromas del Alma"
6. ¡Listo! Ahora tienes un icono en tu iPad

#### Paso 3: Usar sin Internet

- La primera vez necesitas internet para cargar la app
- Después de eso, funcionará **completamente offline**
- Los datos se guardan localmente en el iPad

---

### Opción 2: Netlify (Más Fácil, GRATIS)

1. Ve a https://app.netlify.com/drop
2. Arrastra la carpeta completa del proyecto
3. Netlify te dará una URL automáticamente
4. Sigue los mismos pasos del iPad (Safari > Compartir > Añadir)

---

### Opción 3: Vercel (GRATIS)

1. Ve a https://vercel.com
2. Importa tu proyecto desde GitHub
3. Deploya con un clic
4. Sigue los pasos de instalación en iPad

---

## 🚀 Alternativa: Usar Localmente (Sin Internet desde el Inicio)

Si prefieres no usar servicios en línea, puedes usar un servidor local:

### En tu computadora:

```bash
# Instala Python si no lo tienes
# Luego en la carpeta del proyecto:

python3 -m http.server 8000
```

### En el iPad:

1. Asegúrate de estar en la misma red WiFi que tu computadora
2. Abre Safari
3. Ve a: `http://IP-DE-TU-COMPUTADORA:8000`
4. Añade a pantalla de inicio

**Nota:** Esta opción requiere que tu computadora esté encendida y en la misma red.

---

## 📋 Archivos Incluidos

```
📁 aromas-pwa/
├── index.html           # Página principal (ventas)
├── agregar.html         # Formulario para nuevos productos
├── app.js              # Lógica de la aplicación
├── db.js               # Base de datos IndexedDB
├── styles.css          # Estilos
├── manifest.json       # Configuración PWA
├── service-worker.js   # ⭐ NUEVO: Caché offline
├── icon-192.png        # ⭐ NUEVO: Icono pequeño
├── icon-512.png        # ⭐ NUEVO: Icono grande
└── README.md           # Este archivo
```

---

## 💡 Consejos de Uso

### En el iPad:

1. **Instalar la app:** Safari > Compartir > Añadir a pantalla de inicio
2. **Modo sin conexión:** Después de la primera carga, funciona offline
3. **Escáner:** Puedes usar un lector de códigos de barras Bluetooth
4. **Pantalla completa:** La app se abre sin las barras de Safari

### Funcionalidades:

- **Agregar al carrito:** Escanea código → Cantidad → Agregar
- **Descuentos:** En el carrito, ingresa % de descuento
- **Reposición:** Botón azul "Sumar al Stock"
- **Inventario:** Botón "Ver Inventario" muestra todos los productos
- **Nuevos productos:** Botón "Agregar Producto"

---

## 🔧 Actualizar la App

Si haces cambios al código:

1. Sube los archivos nuevos a tu hosting
2. En el iPad, abre la app
3. Cierra completamente la app (desliza hacia arriba)
4. Vuelve a abrirla
5. El service worker se actualizará automáticamente

O puedes cambiar la versión en `service-worker.js`:
```javascript
const CACHE_NAME = 'aromas-del-alma-v2'; // Cambia v1 → v2
```

---

## ❓ Preguntas Frecuentes

**P: ¿Se pierden los datos si cierro la app?**  
R: No, todo se guarda en IndexedDB localmente.

**P: ¿Puedo usar en iPhone también?**  
R: Sí, funciona igual en iPhone/iPad con Safari.

**P: ¿Necesito renovar algo cada año?**  
R: No, GitHub Pages y Netlify son gratis para siempre.

**P: ¿Los datos se sincronizan entre dispositivos?**  
R: No, cada dispositivo tiene su propia base de datos local.

**P: ¿Funciona en Android?**  
R: Sí, en Chrome/Firefox igual se puede instalar.

---

## 🆘 Solución de Problemas

**"No puedo instalar la app"**
- Asegúrate de usar Safari (no Chrome) en iOS
- Verifica que la URL tenga HTTPS (necesario para PWA)

**"No funciona offline"**
- Abre la app al menos una vez con internet
- Verifica que el service worker esté registrado (consola del navegador)

**"Los datos desaparecieron"**
- Si limpias el caché del navegador, se borran los datos
- Recomiendo no limpiar el caché de Safari si usas esta app

---

## 📞 Soporte

Para dudas o problemas, contacta al desarrollador.

---

**¡Disfruta tu nueva app de ventas! 🌸✨**
