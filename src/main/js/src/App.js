import logo from './logo.svg';
import { useEffect, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-icons/font/bootstrap-icons.css';
import './App.css';
require('bootstrap');


function Header({text}) {
  return (
    <nav class="navbar bg-body-secondary">
      <div class="container-fluid">
        <span class="navbar-brand mb-0 h1">{text}</span>
      </div>
    </nav>
  );
}

function SideBar({entities, selectEntity}) {
  const entityList = entities.map(entity => <li><a className="link-secondary" href='JavaScript:;' onClick={() => selectEntity(entity.type)}>{entity.name}</a></li>);
  return (
    <div>
      <ul className="ps-2">
        {entityList}
      </ul>
    </div>
  );
}

function TableTop({entity}) {
  return (
    <div class="row">
      <div class="col">
        <div class="input-group mb-3">
          //<input type="text" class="form-control" placeholder="Search..." aria-label="Search" aria-describedby="searchButton" />
          <button class="btn btn-outline-secondary" type="button" id="searchButton"><i class="bi bi-search"></i></button>
        </div>
      </div>
      <div class="col">
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
          <button type="button" class="btn btn-outline-secondary"><i class="bi bi-file-earmark-plus"></i> New</button>
        </div>
      </div>
    </div>
  );
}

function Table({entity}) {
  const [data, setData] = useState([]);

  const tableHeaders = entity.attributes.map(attr => <th scope="col">{attr.name}</th>);
  const dataRows = !data || data.length == 0 ? <tr><td colspan={entity.attributes.length}>No record found.</td></tr> : data.map(d =>
    <tr>
      {entity.attributes.map(attr =>
        <td>
          {d[attr.name]}
        </td>
      )}
    </tr>
  );

  useEffect(() => {
      fetch('/easy-admin/entities/' + entity.name + '/data')
            .then(response => response.json())
            .then(json => setData(json))
            .catch(error => console.error(error));
    }, [entity]);

  return (
    <div className="table-responsive-sm">
      <table className="table">
        <thead>
          <tr>
            {tableHeaders}
          </tr>
        </thead>
        <tbody>
          {dataRows}
        </tbody>
      </table>
    </div>
  );
}

function Crud({entity}) {
   return (
     <div>
       <TableTop entity={entity} />
       <Table entity={entity} />
     </div>
   );
}

function Content({entity}) {
  if (entity) {
    return (<div><Crud entity={entity} /></div>);
  } else {
    return (<div>Select an entity to manage.</div>);
  }
}

function App() {
  const [entities, setEntities] = useState([]);
  const [selectedEntity, setSelectedEntity] = useState(null);

  function selectEntity(typeName) {
    setSelectedEntity(entities.find(e => e.type === typeName));
  }

  useEffect(() => {
    fetch('/easy-admin/entities')
          .then(response => response.json())
          .then(json => setEntities(json))
          .catch(error => console.error(error));
  }, []);

  return (
    <div className="App container">
      <div className="row mb-3">
        <Header text="Easy Admin"/>
      </div>
      <div className="row">
        <div className="col-2">
          <SideBar entities={entities} selectEntity={selectEntity}/>
        </div>
        <div className="col-10">
          <Content entity={selectedEntity}/>
        </div>
      </div>
    </div>
  );
}

export default App;
