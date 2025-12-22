// Configuración de IndexedDB
const DB_NAME = 'aromasDB';
const DB_VERSION = 1;

let dbPromise = new Promise((resolve, reject) => {
  const request = indexedDB.open(DB_NAME, DB_VERSION);

  request.onerror = () => reject(request.error);
  request.onsuccess = () => resolve(request.result);

  request.onupgradeneeded = (event) => {
    const db = event.target.result;
    if (!db.objectStoreNames.contains('productos')) {
      const store = db.createObjectStore('productos', { keyPath: 'codigoBarras' });
      store.createIndex('nombre', 'nombre');
    }
  };
});

// Guardar producto
async function guardarProducto(producto) {
  const db = await dbPromise;
  const tx = db.transaction('productos', 'readwrite');
  await tx.objectStore('productos').put(producto);
  await tx.done;
}

// Buscar producto
async function buscarProducto(codigo) {
  const db = await dbPromise;
  const tx = db.transaction('productos', 'readonly');
  const producto = await tx.objectStore('productos').get(codigo);
  await tx.done;
  return producto;
}

// Obtener todos los productos
async function obtenerTodosProductos() {
  const db = await dbPromise;
  const tx = db.transaction('productos', 'readonly');
  const productos = await tx.objectStore('productos').getAll();
  await tx.done;
  return productos;
}