// Estado
let carrito = JSON.parse(localStorage.getItem('carrito')) || [];
let descuentoPorcentaje = 0;

// Elementos
const $mensaje = document.getElementById('mensaje');
const $codigoBarras = document.getElementById('codigoBarras');
const $cantidad = document.getElementById('cantidad');
const $btnAgregar = document.getElementById('btnAgregar');
const $btnReposicion = document.getElementById('btnReposicion');
const $carrito = document.getElementById('carrito');
const $listaCarrito = document.getElementById('listaCarrito');
const $totales = document.getElementById('totales');
const $btnConfirmar = document.getElementById('btnConfirmar');
const $btnVerInventario = document.getElementById('btnVerInventario');
const $btnAgregarProducto = document.getElementById('btnAgregarProducto');

// Elementos de descuento
const $descuentoInput = document.createElement('input');
const $descuentoBtn = document.createElement('button');
const $descuentoContainer = document.createElement('div');

// Configurar descuento
$descuentoInput.type = 'number';
$descuentoInput.min = '0';
$descuentoInput.max = '100';
$descuentoInput.placeholder = '0';
$descuentoInput.style.width = '80px';
$descuentoInput.style.padding = '6px';
$descuentoInput.style.marginRight = '10px';

$descuentoBtn.textContent = 'Aplicar';
$descuentoBtn.className = 'btn-secondary';
$descuentoBtn.style.width = 'auto';
$descuentoBtn.style.padding = '6px 12px';
$descuentoBtn.style.marginTop = '0';

$descuentoContainer.style.margin = '15px 0';
$descuentoContainer.innerHTML = '<label>Descuento (%):</label>';
$descuentoContainer.appendChild($descuentoInput);
$descuentoContainer.appendChild($descuentoBtn);

// Mostrar mensaje
function mostrarMensaje(texto, tipo = 'success') {
  $mensaje.textContent = texto;
  $mensaje.className = `mensaje ${tipo}`;
  $mensaje.style.display = 'block';
  if (tipo === 'success') {
    setTimeout(() => { $mensaje.style.display = 'none'; }, 1000);
  }
}

// Calcular total
function calcularTotales() {
  const totalSinDescuento = carrito.reduce((sum, item) => sum + (item.precio * item.cantidad), 0);
  let totalConDescuento = totalSinDescuento;
  if (descuentoPorcentaje > 0) {
    totalConDescuento = totalSinDescuento * (1 - descuentoPorcentaje / 100);
  }
  return { totalSinDescuento, totalConDescuento };
}

// Renderizar carrito
function renderCarrito() {
  if (carrito.length === 0) {
    $carrito.style.display = 'none';
    return;
  }
  $carrito.style.display = 'block';
  
  $listaCarrito.innerHTML = carrito.map(item => `
    <div class="carrito-item">
      <div>${item.nombre} (x${item.cantidad}) - $${item.precio.toFixed(2)}</div>
      <div>$${(item.precio * item.cantidad).toFixed(2)}</div>
    </div>
  `).join('');

  const { totalSinDescuento, totalConDescuento } = calcularTotales();
  let totalesHTML = `<div style="text-align: right; margin-bottom: 10px;">`;

  if (descuentoPorcentaje > 0) {
    totalesHTML += `
      <div style="font-size: 14px; color: black; text-decoration: line-through;">
        Total original: $${totalSinDescuento.toFixed(2)}
      </div>
      <div style="font-size: 16px; color: #d32f2f; font-weight: bold;">
        Descuento aplicado: ${descuentoPorcentaje}%
      </div>
    `;
  }

  totalesHTML += `<div style="font-size: 20px; color: #2e7d32; font-weight: bold;">
    Total: $${totalConDescuento.toFixed(2)}
  </div></div>`;

  $totales.innerHTML = totalesHTML;

  // Asegurar que el formulario de descuento esté en el DOM
  if (!$totales.querySelector('label')) {
    $totales.appendChild($descuentoContainer);
  }
}

// Agregar al carrito
$btnAgregar.addEventListener('click', async () => {
  const codigo = $codigoBarras.value.trim();
  const cantidad = parseInt($cantidad.value) || 1;
  if (!codigo) return mostrarMensaje('Ingresa un código de barras', 'error');

  const producto = await buscarProducto(codigo);
  if (!producto) {
    window.location.href = `agregar.html?codigo=${encodeURIComponent(codigo)}`;
    return;
  }

  if (producto.stock < cantidad) {
    mostrarMensaje('Stock insuficiente', 'error');
    return;
  }

  carrito.push({
    codigoBarras: producto.codigoBarras,
    nombre: producto.nombre,
    precio: producto.precio,
    cantidad
  });
  localStorage.setItem('carrito', JSON.stringify(carrito));
  mostrarMensaje('Agregado al carrito');
  $codigoBarras.value = '';
  $codigoBarras.focus();
  renderCarrito();
});

// Reposición de stock
$btnReposicion.addEventListener('click', async () => {
  const codigo = $codigoBarras.value.trim();
  if (!codigo) return mostrarMensaje('Ingresa un código de barras', 'error');

  const producto = await buscarProducto(codigo);
  if (producto) {
    producto.stock += 1;
    await guardarProducto(producto);
    mostrarMensaje('Se agregó al stock');
  } else {
    window.location.href = `agregar.html?codigo=${encodeURIComponent(codigo)}`;
  }
  $codigoBarras.value = '';
  $codigoBarras.focus();
});

// Aplicar descuento
$descuentoBtn.addEventListener('click', () => {
  const valor = parseFloat($descuentoInput.value) || 0;
  if (valor >= 0 && valor <= 100) {
    descuentoPorcentaje = valor;
    renderCarrito();
  } else {
    mostrarMensaje('Descuento debe estar entre 0 y 100', 'error');
  }
});

// Confirmar venta
$btnConfirmar.addEventListener('click', async () => {
  for (const item of carrito) {
    const producto = await buscarProducto(item.codigoBarras);
    if (producto && producto.stock >= item.cantidad) {
      producto.stock -= item.cantidad;
      await guardarProducto(producto);
    }
  }
  carrito = [];
  descuentoPorcentaje = 0;
  localStorage.setItem('carrito', JSON.stringify(carrito));
  mostrarMensaje('¡Venta completada!');
  renderCarrito();
});

// Ver inventario
$btnVerInventario.addEventListener('click', async () => {
  const productos = await obtenerTodosProductos();
  const texto = productos.map(p => 
    `${p.nombre} [${p.codigoBarras}] - Stock: ${p.stock}`
  ).join('\n');
  alert('📦 Inventario:\n\n' + (texto || 'Sin productos'));
});

// Agregar producto
$btnAgregarProducto.addEventListener('click', () => {
  window.location.href = 'agregar.html';
});

// Inicializar
document.addEventListener('DOMContentLoaded', () => {
  renderCarrito();
  $codigoBarras.focus();
});