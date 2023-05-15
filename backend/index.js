const moment = require("moment");
const cors = require("cors");
const express = require("express");
const bodyParser = require("body-parser");
const redis = require("redis");
const hostname = "127.0.0.1";
const port = 5000;

const app = express();

const corsOptions = {
  origin: "*",
  credentials: true,
  optionSuccessStatus: 200,
};

app.use(cors(corsOptions));

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

const redisClient = redis.createClient(6379, "127.0.0.1");
redisClient.connect();

redisClient.on("connect", (err) => {
  console.log("Redis Database Connected");
});

app.get("/get_tracker_points", async (req, res) => {
  console.log("req recieved");
  let key = "tracker_point";
  let tracker_point = await redisClient.get(key);
  if (tracker_point !== null) {
    return res.status(200).send(tracker_point);
  }
  return res.status(200).send({ message: "No Tracker Point Added" });
});

app.post("/update_tracker_points", async (req, res) => {
  let product = {
    img: "https://media.croma.com/image/upload/v1662703724/Croma%20Assets/Communication/Mobiles/Images/261934_qgssvy.png",
    name: "Apple iPhone 14 (128GB, Blue)",
    price: "€1000",
    specification:
      "Key Features Display: 6.1 inches (15.40 cm) OLED Display Memory: 128GB ROM",
    orderstatus: req.body.stage,
    delivary_destination: { lats: 49.414326, lng: 8.650394 },
    delivary_source: {
      lats: req.body.destination.lats,
      lng: req.body.destination.long,
    },
    order_stage: req.body.type,
  };

  console.log("req.body.start", req.body.start);
  if (req.body.start !== "") {
    let key = "order_start";
    let orderstart = await redisClient.get(key);

    if (orderstart !== null && JSON.parse(orderstart).length !== 0) {
      let orderstage = await redisClient.get(key);
      if (orderstage) {
        let oldstage = JSON.parse(orderstage);
        let obj = {
          position: req.body.destination,
          time: moment().toISOString(),
          type: req.body.type,
        };
        oldstage.push(obj);

        await redisClient.set(key, JSON.stringify(oldstage));
      }
    } else {
      let obj = [
        {
          time: moment().format("lll"),
          type: "Processing",
        },
      ];
      await redisClient.set(key, JSON.stringify(obj));
    }
  }

  let key = "product";
  let productcheck = await redisClient.get(key);

  if (productcheck !== null) {
    let productdelete = await redisClient.del(key);
    if (productdelete) {
      redisClient.set(key, JSON.stringify(product));
      let productcheck = await redisClient.get(key);
      return res.status(201).send(productcheck);
    }
  } else {
    await redisClient.set(key, JSON.stringify(product));
    return res.status(201).send(productcheck);
  }
});

app.get("/product_info", async (req, res) => {
  console.log("req recieved");
  let key = "product";
  let product = await redisClient.get(key);
  if (product !== null) {
    return res.status(200).send(product);
  }
  return res.status(200).send({ message: "No Product Found" });
});

app.get("", (req, res) => {
  return res.status(200).send("Backend Server Initialized");
});

app.listen(port, hostname, () => {
  console.log(`Server running at http://${hostname}:${port}/`);
});
