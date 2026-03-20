export default function Alert({ alert, onClose }) {
  if (!alert) return null;

  return (
    <div className="container mt-3">
      <div className={`alert alert-${alert.type} alert-dismissible fade show`} role="alert">
        {alert.message}
        <button type="button" className="btn-close" onClick={onClose}></button>
      </div>
    </div>
  );
}
