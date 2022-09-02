import { Box, Container, TextField, ThemeProvider, Typography } from '@mui/material'
import type { NextPage } from 'next'
import { useState } from 'react'
import theme from '../theme/theme'
import insertDakuten from '../utils/insertDakuten'

const Home: NextPage = () => {
  const [outText, setOutText] = useState('')
  return (
    <ThemeProvider theme={theme}>
      <Container maxWidth="xs">
        <TextField
          onChange={(e) => {
            setOutText(insertDakuten(e.target.value))
          }}
        >
        </TextField>
        <Typography>{outText}</Typography>
      </Container>
    </ThemeProvider>
  )
}

export default Home
