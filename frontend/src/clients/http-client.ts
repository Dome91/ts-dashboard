import axios from "axios";

const baseURL = process.env.BASE_URL

const httpClient = axios.create({
    baseURL,
})

export default httpClient
