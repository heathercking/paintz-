import './App.css';
import { 
  BrowserRouter as Router,
  Routes,
  Route,
  Link
  } from 'react-router-dom';
import PaintzContainer from './containers/PaintzContainer';
import HomePageContainer from './containers/HomePageContainer';
import PaintSearchContainer from './containers/PaintSearchContainer';
import PaintConverterContainer from './containers/PaintConverterContainer';
import LoginContainer from './containers/LoginContainer';
import UserInventoryContainer from './containers/UserInventoryContainer';
import RegisterContainer from './containers/RegisterContainer';

// const cors = require('cors');
// app.use(cors());


function App() {
  
  return (

      <Router>
        <nav>
          <Link to="/home"> Home </Link>
          <Link to="/login"> Login </Link>
          <Link to="/register"> Register </Link>
          <Link to="/"> Paintz </Link>
        </nav>
        <Routes>
          <Route path="/" element={ <PaintzContainer /> } />
          <Route path="/home" element={ <HomePageContainer /> } />
          <Route path="/search" element={ <PaintSearchContainer /> } />
          <Route path="/convert" element={ <PaintConverterContainer /> } />
          <Route path="/login" element={ <LoginContainer /> } />
          <Route path="/register" element={ <RegisterContainer /> } />
          <Route path="/inventory" element={ <UserInventoryContainer /> } />
          </Routes>
      </Router>

  );
}

export default App;
