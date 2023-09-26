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

function TableTop({entity, onNew}) {
  return (
    <div class="row">
      <div class="col">
        <div class="input-group mb-3">
          {/*
          <input type="text" class="form-control" placeholder="Search..." aria-label="Search" aria-describedby="searchButton" />
          <button class="btn btn-outline-secondary" type="button" id="searchButton"><i class="bi bi-search"></i></button>
           */}
        </div>
      </div>
      <div class="col">
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
          <button type="button" class="btn btn-outline-secondary" onClick={onNew}><i class="bi bi-file-earmark-plus"></i> New</button>
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

function List({entity, onNew}) {
   return (
     <div>
       <TableTop entity={entity} onNew={onNew} />
       <Table entity={entity} />
     </div>
   );
}

function TextInput({attribute, data}) {
  return (
    <div class="mb-3">
      <label for={attribute.name}>{attribute.name}</label>
      <input type="text" class="form-control" id={attribute.name} placeholder="" />
    </div>
  );
}

function DateTimeInput({attribute, data}) {
  return (
    <div class="mb-3">
      <label for={attribute.name}>{attribute.name}</label>
      <div class="input-group">
        <input type="date" class="form-control" />
        <input type="time" class="form-control" />
      </div>
    </div>
  );
}

function Create({entity, onCancel}) {
  const inputs = entity.attributes
    .filter(attribute => !attribute.identifier)
    .map(attribute => {
      switch(attribute.type) {
        case 'java.lang.String':
          return (<TextInput attribute={attribute} />);
        case 'java.time.LocalDateTime':
          return (<DateTimeInput attribute={attribute} />);
        default:
          return (<div>Unsupported type.</div>);
      }
    });

  return (
    <form>
      <div class="mb-3">Element Create Form for {entity.name}</div>
      {inputs}
      <div class="d-grid gap-2 d-md-flex justify-content-md-end" role="group">
        <div class="btn-group">
          <button type="button" onClick={onCancel} class="btn btn-secondary">Cancel</button>
          <button type="button" class="btn btn-outline-secondary">Create</button>
        </div>
      </div>
    </form>
   );
}

function Content({entityOperation, selectOperation}) {
  if (entityOperation.entity) {
    if(entityOperation.operation == 'create') {
      return (<Create entity={entityOperation.entity} onCancel={()=>selectOperation('list')}/>);
    } else {
      return (<List entity={entityOperation.entity} onNew={()=>selectOperation('create')} />);
    }
  } else {
    return (<div>Select an entity to manage.</div>);
  }
}

function App() {
  const [entities, setEntities] = useState([]);
  const [entityOperation, setEntityOperation] = useState({entity:null, operation:'list'});

  function selectEntity(typeName) {
    setEntityOperation({entity:entities.find(e => e.type === typeName), operation: 'list'});
  }

  function selectOperation(operation) {
    setEntityOperation({...entityOperation, operation:operation});
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
          <Content entityOperation={entityOperation} selectOperation={selectOperation}/>
        </div>
      </div>
    </div>
  );
}

export default App;
