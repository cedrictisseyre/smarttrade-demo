# SmartTrade demo


A very small project to demo a project with overload on Bean and interface to multiple trading plateformes

## API Docs

Document endpoint is `/swagger-ui.html`.

To access document on a kubernetes env:

```shell
kubectl port-forward $(kubectl get po -l app.kubernetes.io/name=smarttrade-demo --no-headers -o custom-columns=":metadata.name") 8080:8080
```

Then open your browser on [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Start to dev

```sh
docker compose up -d postgres-db
```

#### Get your local code running

Run it with the IDE target `SmarttradeDemoApplication [local]`

The REST server will be available at: `127.0.0.1:8080`
