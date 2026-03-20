import { NavLink } from 'react-router-dom';

export default function Navbar() {
  const navClass = ({ isActive }) => `nav-link${isActive ? ' active' : ''}`;

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
      <div className="container-fluid">
        <NavLink to="/" className="navbar-brand fw-bold">
          SBI Digital
        </NavLink>

        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
          <span className="navbar-toggler-icon"></span>
        </button>

        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav ms-auto">
            <li className="nav-item"><NavLink to="/" className={navClass}>Home</NavLink></li>
            <li className="nav-item"><NavLink to="/about" className={navClass}>About</NavLink></li>
            <li className="nav-item"><NavLink to="/services" className={navClass}>Services</NavLink></li>
            <li className="nav-item"><NavLink to="/netbanking" className={navClass}>Netbanking</NavLink></li>
            <li className="nav-item"><NavLink to="/contact" className={navClass}>Contact</NavLink></li>
          </ul>
        </div>
      </div>
    </nav>
  );
}
