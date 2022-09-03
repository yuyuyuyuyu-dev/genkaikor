import * as React from 'react';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import Snackbar from '@mui/material/Snackbar';
import MuiAlert, { AlertProps } from '@mui/material/Alert';

const Alert = React.forwardRef<HTMLDivElement, AlertProps>(function Alert(
  props,
  ref,
) {
  return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
});

type CustomizedSnackbarsPropsType = {
  buttonLabel: string
  snackbarMessage: string
  onClick: () => void
}

export default function CustomizedSnackbars(props: CustomizedSnackbarsPropsType) {
  const [open, setOpen] = React.useState(false);

  const handleClick = () => {
    props.onClick()
    setOpen(true);
  };

  const handleClose = (event?: React.SyntheticEvent | Event, reason?: string) => {
    if (reason === 'clickaway') {
      return;
    }

    setOpen(false);
  };

  return (
    <Stack spacing={2} sx={{ width: '100%' }}>
      <Button variant="contained" onClick={handleClick}>
        {props.buttonLabel}
      </Button>
      <Snackbar open={open} autoHideDuration={6000} onClose={handleClose}>
        <Alert onClose={handleClose} severity="success" sx={{ width: '100%' }}>
          {props.snackbarMessage}
        </Alert>
      </Snackbar>
    </Stack>
  );
}
