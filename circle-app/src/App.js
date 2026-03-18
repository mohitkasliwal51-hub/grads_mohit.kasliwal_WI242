// Details of Circle
// --------------------
//   Radius : 10
// Diameter: 20
// Perimeter: 62.84
// Area: 314.2

function App() {

  const radius = 10;
  const diameter = 2 * radius;
  const perimeter = 2 * Math.PI * radius;
  const area = Math.PI * radius * radius;

  return (
    <>
      <h2>Details of Circle</h2>
      <hr />

      <p>Radius : {radius}</p>
      <p>Diameter : {diameter}</p>
      <p>Perimeter : {perimeter.toFixed(2)}</p>
      <p>Area : {area.toFixed(1)}</p>
    </>
  );
}

export default App;