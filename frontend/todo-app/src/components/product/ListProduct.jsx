/**
 ** component to list new products
 */
import React from "react";
import CssBaseline from "@material-ui/core/CssBaseline";
import Typography from "@material-ui/core/Typography";
import { Formik } from "formik";
import Grid from "@material-ui/core/Grid";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import Container from "@material-ui/core/Container";
import ProductService from "../../service/ProductService";

export class ListProduct extends React.Component {
  constructor(props) {
    super(props);
  }

  listNewProduct = async values => {
    try {
      const result = await ProductService.listNewProduct(
        values,
        values.imageUrl
      );
    } catch (e) {
      return e;
    }
  };

  render() {
    return (
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <div>
          <Typography component="h1" variant="h5">
            List new product
          </Typography>
          <Formik
            initialValues={{
              productName: "",
              price: 0,
              description: "",
              imageUrl: null
            }}
            validate={values => {}}
            onSubmit={async (values, { setSubmitting }) => {
              await this.listNewProduct(values);
            }}
          >
            {({
              values,
              errors,
              touched,
              handleChange,
              handleBlur,
              handleSubmit,
              isSubmitting,
              setFieldValue
            }) => (
              <form onSubmit={handleSubmit}>
                <Grid container spacing={2}>
                  <Grid item xs={12}>
                    <TextField
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.name}
                      variant="outlined"
                      required
                      fullWidth
                      id="productName"
                      label="productName"
                      name="productName"
                      type="text"
                    />
                  </Grid>
                  <Grid item xs={12}>
                    <TextField
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.price}
                      variant="outlined"
                      required
                      fullWidth
                      name="price"
                      label="price"
                      id="price"
                      type="number"
                    />
                  </Grid>
                  <Grid item xs={12}>
                    <TextField
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.description}
                      variant="outlined"
                      required
                      fullWidth
                      name="description"
                      label="description"
                      id="description"
                      type="text"
                    />
                  </Grid>
                  <Grid item xs={12}>
                    <input
                      accept="image/*"
                      // className={classes.input}
                      onBlur={handleBlur}
                      required
                      name="imageUrl"
                      id="imageUrl"
                      type="file"
                      onChange={event => {
                        setFieldValue("imageUrl", event.currentTarget.files[0]);
                      }}
                    />
                    <label htmlFor="imageUrl">
                      <Button
                        component="span"
                        // className={classes.button}
                      >
                        Upload Product Image
                      </Button>
                    </label>
                  </Grid>
                </Grid>
                <Button
                  type="submit"
                  fullWidth
                  variant="contained"
                  color="primary"
                  disabled={isSubmitting}
                >
                  List new product
                </Button>
              </form>
            )}
          </Formik>
        </div>
      </Container>
    );
  }
}
