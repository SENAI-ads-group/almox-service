import moment from 'moment';

const formatarData = (conteudo : string) : string => {
    return moment.utc(conteudo).isValid() ? moment.utc(conteudo).format('DD/MM/YYYY') : '';
};

const formatarDataHora = (conteudo : string) : string => {
    return moment.utc(conteudo).isValid() ? moment.utc(conteudo).format('DD/MM/YYYY HH:mm:ss') : '';
};

export { formatarData, formatarDataHora };
