import{u as r,a as s,g as i}from"./index-DhO-k3OJ.js";async function d(e){const n=r(),t=new URL(s(n.endpoint,"/api/oobe/init"),location.href);return fetch(t,{headers:i(),body:JSON.stringify(e),method:"POST"}).then(o=>o.json())}async function c(e){const n=r(),t=new URL(s(n.endpoint,"/api/oobe/testDownloader"),location.href);return fetch(t,{method:"POST",headers:i(),body:JSON.stringify(e)}).then(o=>(n.assertResponseLogin(o),o.json()))}export{d as I,c as T};
