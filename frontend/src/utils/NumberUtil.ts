import _ from 'lodash';
import formatNumber from 'simple-format-number';

const roundNumber = (num: number, precision: number | undefined) => Number(_.round(num, precision).toFixed(precision));

const numberToStringMonetario = (num: number, numeroCasas = 2) => {
    if (_.isNumber(num)) {
        const inputConfig = { fractionDigits: numeroCasas, symbols: { grouping: '.', decimal: ',' } };
        return `R$ ${formatNumber(roundNumber(num, numeroCasas), inputConfig)}`;
    }
    return '';
};

const numberToStringPercentual = (num: number, numeroCasas = 2) => {
    if (_.isNumber(num)) {
        const inputConfig = { fractionDigits: numeroCasas, symbols: { grouping: '', decimal: ',' } };
        return `${formatNumber(roundNumber(num, numeroCasas), inputConfig)}%`;
    }
    return '';
};
const numberToStringDecimal = (num: number, numeroCasas = 2) => {
    if (_.isNumber(num)) {
        const inputConfig = { fractionDigits: numeroCasas, symbols: { grouping: '.', decimal: ',' } };
        return `${formatNumber(roundNumber(num, numeroCasas), inputConfig)}`;
    }
    return '';
};

const numberToStringInteiro = (num: number) => {
    if (_.isNumber(num)) {
        return num.toFixed(0);
    }
    return '';
};
export { numberToStringMonetario, numberToStringPercentual, numberToStringDecimal, numberToStringInteiro };
