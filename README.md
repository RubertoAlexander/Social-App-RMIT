# Build Status
[![Build Status](https://travis-ci.com/RMIT-SEPT/JavaBinks.svg?token=pm9eaEqWsiEJzGztmVkj&branch=master)](https://travis-ci.com/RMIT-SEPT/JavaBinks)

# Important URLs
[Frontend](https://sept-assignment-251710.appspot.com/)

[Backend](https://sept-assignment-backend.appspot.com)

## This Project uses React on the frontend and Spring Boot on the backend

## References
- https://github.com/facebook/create-react-app
- https://facebook.github.io/create-react-app/docs/troubleshooting
- https://babeljs.io/repl

## Installation Guides

#### Required Tools

- Node v8+ for npm
- Visual Studio Code - Latest Version
- Java 8+
- Eclipse - Oxygen+ - (Embedded Maven From Eclipse)

#### Installing Node Js (npm) & Visual Studio Code 


- Steps
  - Step 01 - Installing NodeJs and NPM - Node Package Manager
  - Step 02 - Quick Introduction to NPM
  - Step 03 - Installing Visual Studio Code - Front End Java Script Editor

#### Installing Java, Eclipse & Embedded Maven

- Steps
  - 0 - Overview - Installation Java, Eclipse and Maven
  - 1 - Installing Java JDK
  - 2 - Installing Eclipse IDE
  - 3 - Using Embedded Maven in Eclipse
  - 4 - Troubleshooting Java, Eclipse and Maven

#### Troubleshooting Installations

- Node JS and NPM 
  - https://docs.npmjs.com/common-errors
  - https://docs.npmjs.com/getting-started/troubleshooting
- Visual Studio Code
  - https://code.visualstudio.com/docs/supporting/errors
  - https://code.visualstudio.com/docs/supporting/FAQ
- Eclipse and Embedded Maven
  - PDF : https://github.com/sept/Springsept/blob/master/InstallationGuide-JavaEclipseAndMaven_v2.pdf
  - GIT Repository For Installation : https://github.com/sept/getting-started-in-5-steps

### Introduction
Developing your first full stack application with React and Spring Boot is fun.

You will be using React (Frontend View Framework), React Create App(To create React project), Various JavaScript Libraries (Axios, Formik, React Router), Spring Boot (REST API Framework), Spring (Dependency Management),  Spring Security (Authentication and Authorization - Basic and JWT), BootStrap (Styling Pages), Maven (dependencies management), Node (npm), Visual Studio Code (JavaScript IDE), Eclipse (Java IDE) and Tomcat Embedded Web Server. We will help you set up each one of these.

```

## Code Snippets

### Core JWT Components

```properties
jwt.signing.key.secret=mySecret
jwt.get.token.uri=/authenticate
jwt.refresh.token.uri=/refresh
jwt.http.request.header=Authorization
jwt.token.expiration.in.seconds=604800
```

---

```

`
#Global
npm uninstall -g React-cli
npm cache verify
npm install -g @React/cli@7.0.3

#Inside the project - If you had an earlier version of React cli
rm -rf node_modules
npm uninstall --save-dev React-cli
npm install --save-dev @React/cli@latest
npm install
```
- Why Visual Studio Code?
  - https://trends.google.com/trends/explore?date=all&q=%2Fm%2F0k2kj45,%2Fm%2F0_x5x3g,%2Fm%2F0134xwrk,%2Fm%2F0b6h18n
- We use Light Theme
- Install
    - Auto Import - Automatically finds, parses and provides code actions and code completion for all available imports. Works with Typescript and TSX
    - Reload to Activate

## What You will Learn?

### Big Picture
- What is the High Level Architecture of our Full Stack Application?
- What is an SPA?
- What is React?

### TypeScript and JavaScript
- I'm new to TypeScript. Will I be able to adapt to it?
- How does a JavaScript Class compare to a Java Class?
  - Packages vs Modules
  - import statements
  - Decorator vs Annotation
- What is a JavaScript Module?
- How does JavaScript Syntax compare to Java Syntax?
  - Arrays - Filtering, Spread Operator and Functional Stuff
  - Custom Objects

### React Basics 
- What is React Component?
- What are the conventions for file extensions in React Projects?
- How do you build forms in React? How do you do Form Validation?
- What is Routing?
- How do you implement Routing in React?
- How do you call HTTP Services in React?

### Running React Applications
- What is Root Component? What are Bootstrap Components? How is the React Application Bootstrapped?  ```\src\index.html```, ```\src\main.ts```, ```AppModule```, ```AppComponent```
- Do Browsers understand JSX? How does JSX code get converted to JavaScript code? 

## Automated Tests and Code Quality
- What are unit tests? How are unit tests organized in React? How is different from Java?
- How can you run tests? ```\src\karma.conf.ts```
- What are coding standards? How can you check coding standards for React Cli Project? What is Lint? What is Linting? Is there a Standard Style Guide for React? ```\tslint.json```
- How can I run coding standards check for React Projects?

## Course Details


### Request URLs and Examples

#### Common Headers

```
Origin - http://localhost:3000
Content-Type - application/json
Authorization 
- Bearer *** or
- Basic *****
```


#### Retrieve all todos for a user 

- GET - http://localhost:8080/users/sept/todos

```
[
  {
    id: 1,
    username: "sept",
    description: "Learn to Dance 2",
    targetDate: "2018-11-09T12:05:18.647+0000",
   : false,
  },
  {
    id: 2,
    username: "sept",
    description: "Learn about Microservices 2",
    targetDate: "2018-11-09T12:05:18.647+0000",
   : false,
  },
  {
    id: 3,
    username: "sept",
    description: "Learn about React",
    targetDate: "2018-11-09T12:05:18.647+0000",
   : false,
  },
]
```

#### Retrieve a specific todo

- GET - http://localhost:8080/users/sept/todos/1

```
{
  id: 1,
  username: "sept",
  description: "Learn to Dance 2",
  targetDate: "2018-11-09T12:05:18.647+0000",
 : false,
}
```

#### Creating a new todo

- POST to http://localhost:8080/users/sept/todos with BODY of Request given below

```
{
  "username": "sept",
  "description": "Learn to Drive a Car",
  "targetDate": "2018-11-09T10:49:23.566+0000",
  "done": false
}
```

#### Updating a new todo

- http://localhost:8080/users/sept/todos/1 with BODY of Request given below

```
{
  "id": 1
  "username": "sept",
  "description": "Learn to Drive a Car",
  "targetDate": "2018-11-09T10:49:23.566+0000",
  "done": false
}
```

#### Delete todo

- DELETE to http://localhost:8080/users/sept/todos/1

### JWT Authenticate

- POST to http://localhost:8080/authenticate


```
{
  "username":"ranga",
  "password":"password@!23@#!"
}
```

Response

```
{
"token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyYW5nYSIsImV4cCI6MTU0MjQ3MjA3NCwiaWF0IjoxNTQxODY3Mjc0fQ.kD6UJQyxjSPMzAhoTJRr-Z5UL-FfgsyxbdseWQvk0fLi7eVXAKhBkWfj06SwH43sY_ZWBEeLuxaE09szTboefw"
}
```

Other URLS
- Refresh - http://localhost:8080/authenticate


  

## Connection to MySQL

```
create sequence hibernate_sequence start with 1 increment by 1

create table todo (
    id bigint not null, 
    description varchar(255), 
    is_done boolean not null, 
    target_date timestamp, 
    username varchar(255), 
    primary key (id))

```

## Diagrams

- Courtesy http://viz-js.com/

```

digraph architecture {
node[style=filled,color="#59C8DE",fontsize=20]
//node [style=filled,color="#D14D28", fontcolor=white];
edge [fontsize=6 ];

VIRTUALDOM[label=<Virtual DOM>];
DOM
REACTAPP[label=<App>];

{rank=same; DOM, REACTAPP};

VIRTUALDOM -> DOM [ label="diff & update" ];
REACTAPP -> VIRTUALDOM [ label="creates" ];
DOM -> REACTAPP [ label="events" ];

}

digraph architecture {
node[style=filled,color="#59C8DE",fontsize=20]
//node [style=filled,color="#D14D28", fontcolor=white];
edge [fontsize=9 ];
{rank=same; Actions, Reducers, Store};

Actions -> Reducers
View -> Actions [ label="dispatch" ];
Store -> View [label ="subscribe"]
Reducers -> Store

}


  
graph architecture {
node[style=filled,color="#59C8DE"]
//node [style=filled,color="#D14D28", fontcolor=white];
rankdir = TB;
node[shape=record]

FRONTEND[label=<React Application<BR />
   <FONT POINT-SIZE="9">Modern JavaScript - ES6</FONT>>];

REST[label=<RESTFUL API<BR />
   <FONT POINT-SIZE="9">Spring Boot on Java</FONT>>];

DB[label=<Database>];

FRONTEND -- REST -- DB
DB[shape=cylinder]
}

digraph architecture {
node[style=filled,color="#59C8DE"]
//node [style=filled,color="#D14D28", fontcolor=white];
rankdir = TB;
node[shape=record]

FRONTEND[label=<React Application<BR />
   <FONT POINT-SIZE="9">JavaScript</FONT>>];

MODULE0[label=<Components>];

MODULE1[label=<Libraries>];

COMPONENT01[label=<Login>];
COMPONENT02[label=<Logout>];
COMPONENT03[label=<ListTodo>];
COMPONENT04[label=<Todo>];
COMPONENT05[label=<Header>];
COMPONENT06[label=<Footer>];
COMPONENT07[label=<Menu>];

COMPONENT11[label=<Formik>];
COMPONENT12[label=<Axios>];
COMPONENT13[label=<ReactRouter>];

FRONTEND -> MODULE0
FRONTEND -> MODULE1

MODULE0 -> COMPONENT01
MODULE0 -> COMPONENT02
MODULE0 -> COMPONENT03
MODULE0 -> COMPONENT04
MODULE0 -> COMPONENT05
MODULE0 -> COMPONENT06
MODULE0 -> COMPONENT07

MODULE1 -> COMPONENT11
MODULE1 -> COMPONENT12
MODULE1 -> COMPONENT13

}


graph architecture {

node[style=filled,color="#59C8DE"]
//node [style=filled,color="#D14D28", fontcolor=white];
rankdir = TB;
node[shape=record]

COMPONENT[label=<Component>];

View[label=<View<BR />
   <FONT POINT-SIZE="9">JSX or Javascript</FONT>>];
Logic[label=<Logic<BR />
   <FONT POINT-SIZE="9">Javascript</FONT>>];
Styling[label=<Styling<BR />
   <FONT POINT-SIZE="9">CSS</FONT>>];
State[label=<State<BR />
   <FONT POINT-SIZE="9">Internal Data Store</FONT>>];
Props[label=<Props<BR />
   <FONT POINT-SIZE="9">Pass Data</FONT>>];

COMPONENT -- View
COMPONENT -- Logic
COMPONENT -- Styling
COMPONENT -- State
COMPONENT -- Props
}

graph architecture {

node[style=filled,color="#59C8DE"]
//node [style=filled,color="#D14D28", fontcolor=white];
rankdir = TB;
node[shape=record]

React -- Components
Components -- JSX
Components -- State
Components -- Props
React -- Features
Features -- Routing
Features -- Forms
Features -- RestAPICalls
Features -- Authentication

RestAPICalls[label=<Rest API Calls>]
Forms[label=<Forms and Validation>]

}


```

## Todo
- Debugging with Visual Studio Code
  - To debug the client side React code, we'll need to install the Debugger for Chrome extension - https://marketplace.visualstudio.com/items?itemName=msjsdiag.debugger-for-chrome
  - Open the Extensions view (⇧⌘X or Ctrl+Shift+X)
  - Type Debugger for Chrome
  - Install
  - Reload
  - Go to the Debug view (⇧⌘D or Ctrl+Shift+D)
  - Click on gear button to create launch.json
  - Choose Chrome from the Select Environment dropdown
  - Set URL to "url": "http://localhost:3000"
- Running Examples
  - Download the zip or clone the Git repository.
  - Unzip the zip file (if you downloaded one)
  - Open Command Prompt and Change directory (cd) to folder containing pom.xml
  - Open Eclipse 
     - File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
     - Select the right project
  - Choose the Spring Boot Application file (search for file with @SpringBootApplication)
  - Right Click on the file and Run as Java Application
  - You are all Set
  - For help : use our installation guide - A video will be uploaded for this soon

## Next Steps
- React
  - Why we need to bind event handlers in Class Components in React?
    - https://medium.freecodecamp.org/this-is-why-we-need-to-bind-event-handlers-in-class-components-in-react-f7ea1a6f93eb
  - class vs className - A discussion
    - https://stackoverflow.com/questions/46989454/class-vs-classname-in-react-16
  - 
- Modern JavaScript 
  - https://github.com/mbeaudru/modern-js-cheatsheet#tdz_sample
  - https://learnxinyminutes.com/docs/javascript/
  - https://github.com/mjavascript/mastering-modular-javascript/blob/master/chapters/ch01.asciidoc
  - https://developer.mozilla.org/en-US/docs/Web/JavaScript/A_re-introduction_to_JavaScript
  - Modern Javascript Quickly - https://gist.github.com/gaearon/683e676101005de0add59e8bb345340c
- React
  - https://raw.githubusercontent.com/reactjs/reactjs.org/master/static/html/single-file-example.html
  - class vs className - https://stackoverflow.com/questions/46989454/class-vs-classname-in-react-16
  - https://engineering.musefind.com/react-lifecycle-methods-how-and-when-to-use-them-2111a1b692b1
  - https://reactjs.org/blog/2018/03/29/react-v-16-3.html#component-lifecycle-changes