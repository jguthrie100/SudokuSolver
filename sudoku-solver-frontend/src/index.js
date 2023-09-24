import './index.css';
import React from 'react';
import ReactDOM from 'react-dom/client';
import Board from './model/Board/Board';

// Bootstrap CSS
import "bootstrap/dist/css/bootstrap.min.css";
// Bootstrap Bundle JS
import "bootstrap/dist/js/bootstrap.bundle.min";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <div id="main">
      <div />
      <Board />
      <div />
    </div>
  </React.StrictMode>
);